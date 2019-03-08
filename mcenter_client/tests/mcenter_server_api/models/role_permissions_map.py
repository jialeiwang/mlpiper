# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from mcenter_server_api.models.base_model_ import Model
from mcenter_server_api import util


class RolePermissionsMap(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, model=None, ion_pattern=None, sandbox_ion_instance=None, component=None, production_ion_instance=None, profile=None, pipeline=None):  # noqa: E501
        """RolePermissionsMap - a model defined in OpenAPI

        :param model: The model of this RolePermissionsMap.  # noqa: E501
        :type model: int
        :param ion_pattern: The ion_pattern of this RolePermissionsMap.  # noqa: E501
        :type ion_pattern: int
        :param sandbox_ion_instance: The sandbox_ion_instance of this RolePermissionsMap.  # noqa: E501
        :type sandbox_ion_instance: int
        :param component: The component of this RolePermissionsMap.  # noqa: E501
        :type component: int
        :param production_ion_instance: The production_ion_instance of this RolePermissionsMap.  # noqa: E501
        :type production_ion_instance: int
        :param profile: The profile of this RolePermissionsMap.  # noqa: E501
        :type profile: int
        :param pipeline: The pipeline of this RolePermissionsMap.  # noqa: E501
        :type pipeline: int
        """
        self.openapi_types = {
            'model': 'int',
            'ion_pattern': 'int',
            'sandbox_ion_instance': 'int',
            'component': 'int',
            'production_ion_instance': 'int',
            'profile': 'int',
            'pipeline': 'int'
        }

        self.attribute_map = {
            'model': 'Model',
            'ion_pattern': 'ION Pattern',
            'sandbox_ion_instance': 'Sandbox ION Instance',
            'component': 'Component',
            'production_ion_instance': 'Production ION Instance',
            'profile': 'Profile',
            'pipeline': 'Pipeline'
        }

        self._model = model
        self._ion_pattern = ion_pattern
        self._sandbox_ion_instance = sandbox_ion_instance
        self._component = component
        self._production_ion_instance = production_ion_instance
        self._profile = profile
        self._pipeline = pipeline

    @classmethod
    def from_dict(cls, dikt) -> 'RolePermissionsMap':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Role_permissions_map of this RolePermissionsMap.  # noqa: E501
        :rtype: RolePermissionsMap
        """
        return util.deserialize_model(dikt, cls)

    @property
    def model(self):
        """Gets the model of this RolePermissionsMap.


        :return: The model of this RolePermissionsMap.
        :rtype: int
        """
        return self._model

    @model.setter
    def model(self, model):
        """Sets the model of this RolePermissionsMap.


        :param model: The model of this RolePermissionsMap.
        :type model: int
        """

        self._model = model

    @property
    def ion_pattern(self):
        """Gets the ion_pattern of this RolePermissionsMap.


        :return: The ion_pattern of this RolePermissionsMap.
        :rtype: int
        """
        return self._ion_pattern

    @ion_pattern.setter
    def ion_pattern(self, ion_pattern):
        """Sets the ion_pattern of this RolePermissionsMap.


        :param ion_pattern: The ion_pattern of this RolePermissionsMap.
        :type ion_pattern: int
        """

        self._ion_pattern = ion_pattern

    @property
    def sandbox_ion_instance(self):
        """Gets the sandbox_ion_instance of this RolePermissionsMap.


        :return: The sandbox_ion_instance of this RolePermissionsMap.
        :rtype: int
        """
        return self._sandbox_ion_instance

    @sandbox_ion_instance.setter
    def sandbox_ion_instance(self, sandbox_ion_instance):
        """Sets the sandbox_ion_instance of this RolePermissionsMap.


        :param sandbox_ion_instance: The sandbox_ion_instance of this RolePermissionsMap.
        :type sandbox_ion_instance: int
        """

        self._sandbox_ion_instance = sandbox_ion_instance

    @property
    def component(self):
        """Gets the component of this RolePermissionsMap.


        :return: The component of this RolePermissionsMap.
        :rtype: int
        """
        return self._component

    @component.setter
    def component(self, component):
        """Sets the component of this RolePermissionsMap.


        :param component: The component of this RolePermissionsMap.
        :type component: int
        """

        self._component = component

    @property
    def production_ion_instance(self):
        """Gets the production_ion_instance of this RolePermissionsMap.


        :return: The production_ion_instance of this RolePermissionsMap.
        :rtype: int
        """
        return self._production_ion_instance

    @production_ion_instance.setter
    def production_ion_instance(self, production_ion_instance):
        """Sets the production_ion_instance of this RolePermissionsMap.


        :param production_ion_instance: The production_ion_instance of this RolePermissionsMap.
        :type production_ion_instance: int
        """

        self._production_ion_instance = production_ion_instance

    @property
    def profile(self):
        """Gets the profile of this RolePermissionsMap.


        :return: The profile of this RolePermissionsMap.
        :rtype: int
        """
        return self._profile

    @profile.setter
    def profile(self, profile):
        """Sets the profile of this RolePermissionsMap.


        :param profile: The profile of this RolePermissionsMap.
        :type profile: int
        """

        self._profile = profile

    @property
    def pipeline(self):
        """Gets the pipeline of this RolePermissionsMap.


        :return: The pipeline of this RolePermissionsMap.
        :rtype: int
        """
        return self._pipeline

    @pipeline.setter
    def pipeline(self, pipeline):
        """Sets the pipeline of this RolePermissionsMap.


        :param pipeline: The pipeline of this RolePermissionsMap.
        :type pipeline: int
        """

        self._pipeline = pipeline